package com.akocharyan.cachereader.features.cache.domain.helper.extensions

import android.content.ContentResolver
import android.content.UriPermission
import com.akocharyan.cachereader.features.cache.domain.models.AppEnum

fun ContentResolver.getAppDirectoryPermission(app: AppEnum): UriPermission? =
    persistedUriPermissions.find { it.uri.path?.contains(app.cacheDir) == true }
