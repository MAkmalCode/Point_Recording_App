package com.malbyte.pointrecordingapp.feature_poin_recorder.data.data_source.local

import com.chibatching.kotpref.KotprefModel

object LocalUser: KotprefModel() {
    var id by nullableStringPref("")
    var username by nullableStringPref("")
    var position by nullableStringPref("")
    var poin by intPref(0)
    var image by nullableStringPref(null)
    var loginStatus by booleanPref(false)
}