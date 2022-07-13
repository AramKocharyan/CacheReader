package com.akocharyan.crypto.features.currencies.presenter.model

import com.akocharyan.core.util.network.Error
import com.akocharyan.crypto.features.currencies.domain.models.AppEnum

data class PermissionNotGranted(val app: AppEnum) : Error.FeatureError
