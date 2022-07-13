package com.akocharyan.crypto.features.currencies.domain.helper.extensions

import android.content.ContentResolver
import android.content.UriPermission
import com.akocharyan.crypto.features.currencies.domain.models.AppEnum

fun ContentResolver.getAppDirectoryPermission(app: AppEnum): UriPermission? =
    persistedUriPermissions.find { it.uri.path?.contains(app.cacheDir) == true }
