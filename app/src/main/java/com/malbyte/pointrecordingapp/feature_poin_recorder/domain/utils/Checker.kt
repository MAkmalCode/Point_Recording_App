package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.utils

import android.util.Patterns
import java.util.regex.Pattern

fun String.emailChecker() =  !Patterns.EMAIL_ADDRESS.matcher(this).matches()
