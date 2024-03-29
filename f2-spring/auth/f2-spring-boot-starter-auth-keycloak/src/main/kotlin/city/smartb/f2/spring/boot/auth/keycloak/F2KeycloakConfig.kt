package city.smartb.f2.spring.boot.auth.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "f2")
data class F2KeycloakConfig (
    val issuers: List<KeycloakIssuers> = emptyList()
) {
    fun getConfig(): Map<String, KeycloakConfig> {
        return issuers.associate {
            it.name to KeycloakConfig(
                realm = it.realm,
                authServerUrl = it.authUrl,
                resource = it.web?.clientId
            )
        }
    }
}
