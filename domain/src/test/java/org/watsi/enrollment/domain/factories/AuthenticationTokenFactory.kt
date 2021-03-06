package org.watsi.enrollment.domain.factories

import org.threeten.bp.Instant
import org.watsi.enrollment.domain.entities.AuthenticationToken
import org.watsi.enrollment.domain.entities.User

object AuthenticationTokenFactory {

    fun build(token: String = "p4jE8GMY.Zd6dH8vMYJmXSmow6shNybjspEFGBGz3",
              expiresAt: Instant = Instant.now(),
              user: User = UserFactory.build()) : AuthenticationToken {
        return AuthenticationToken(token = token, expiresAt = expiresAt, user = user)
    }
}
