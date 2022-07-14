package com.akocharyan.cachereader.features.cache.presenter.model

import com.akocharyan.core.models.Error
import com.akocharyan.cachereader.features.cache.domain.models.AppEnum

data class PermissionNotGranted(val app: AppEnum) : Error.FeatureError
