package org.watsi.enrollment.domain.entities

import java.util.UUID

data class Delta(val id: Int = 0,
                 val action: Action,
                 val modelName: ModelName,
                 val modelId: UUID,
                 val field: String? = null,
                 val synced: Boolean = false) {

    enum class Action { ADD, EDIT }
    enum class ModelName {
        HOUSEHOLD,
        HOUSEHOLD_ENROLLMENT_RECORD,
        MEMBER,
        MEMBER_ENROLLMENT_RECORD,
        MEMBERSHIP_PAYMENT,
        PHOTO
    }
}
