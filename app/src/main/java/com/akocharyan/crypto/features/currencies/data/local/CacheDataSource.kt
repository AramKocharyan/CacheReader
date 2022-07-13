package com.akocharyan.crypto.features.currencies.data.local

import android.content.ContentResolver
import android.net.Uri
import android.provider.DocumentsContract
import com.akocharyan.crypto.features.currencies.data.model.CacheDto
import javax.inject.Inject

class CacheDataSource @Inject constructor(private val contentResolver: ContentResolver) {

    fun fetchCache(uri: Uri): List<CacheDto> {
        val result = mutableListOf<CacheDto>()

        val finalUri = DocumentsContract.buildChildDocumentsUriUsingTree(
            uri,
            DocumentsContract.getTreeDocumentId(uri)
        )
        contentResolver.query(
            finalUri,
            arrayOf(
                DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                DocumentsContract.Document.COLUMN_SIZE,
            ),
            null,
            null,
            null
        )?.apply {
            try {
                moveToFirst()
                while (!isAfterLast) {
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
            } catch (e: Exception) {
                return result
            }
        }
        return result
    }

    private fun getFileExtension(fileName: String): String = fileName.replace(Regex("^.*\\.([\\w]+)$"), "$1")

}
