package com.auth.template.auth.support.response

class AuthResponseCode {
    companion object {
        const val AUTH_00 = "Auth-000" // success
        const val AUTH_01 = "Auth-001" // Not valid token
        const val AUTH_02 = "Auth-002" // Damaged token
        const val AUTH_03 = "Auth-003" // Wrong authentication
        const val AUTH_04 = "Auth-004" // Expired token
        const val AUTH_05 = "Auth-005" // Auth User not found
        const val AUTH_06 = "Auth-006" // User Register Fail: User Already Exist
    }
}
