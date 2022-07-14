package com.akocharyan.cachereader.features.cache.data.local

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import com.akocharyan.cachereader.features.cache.data.model.CacheDto
import com.akocharyan.core.models.PagedList
import javax.inject.Inject

class CacheDataSource @Inject constructor(private val contentResolver: ContentResolver) {

    fun fetchCache(uri: Uri, nextPage: Int, pageSize: Int): PagedList<CacheDto> {
        val result = mutableListOf<CacheDto>()

        val finalUri = DocumentsContract.buildChildDocumentsUriUsingTree(
            uri,
            DocumentsContract.getTreeDocumentId(uri)
        )

        val offset = nextPage * pageSize

        val cursor = contentResolver.query(
            finalUri,
            arrayOf(
                DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                DocumentsContract.Document.COLUMN_SIZE,
            ),
            Bundle().apply { // seems to be not working (investigate)
                putInt(ContentResolver.QUERY_ARG_LIMIT, pageSize)
                putInt(ContentResolver.QUERY_ARG_OFFSET, offset)
            },
            null,
        )

        cursor?.apply {
            try {
                val totalCount = this.count
                moveToPosition((nextPage - 1) * pageSize)
                while (!isAfterLast && position < offset) {
                    val documentId = getString(getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID))
                    val fileName = getString(getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME))
                    val size = getLong(getColumnIndex(DocumentsContract.Document.COLUMN_SIZE))
                    val fileExtension = getFileExtension(fileName)
                    result.add(
                        CacheDto(
                            id = documentId,
                            name = fileName,
                            ext = fileExtension,
                            size = size
                        )
                    )
                    moveToNext()
                }
                close()
                return PagedList(result, nextPage + 1, totalCount)
            } catch (e: Exception) {
                return PagedList(result)
            }
        }
        return PagedList(result)
    }

    private fun getFileExtension(fileName: String): String = fileName.replace(Regex("^.*\\.([\\w]+)$"), "$1")

}
