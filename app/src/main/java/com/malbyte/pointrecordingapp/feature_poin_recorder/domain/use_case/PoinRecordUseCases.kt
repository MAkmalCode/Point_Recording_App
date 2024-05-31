package com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case

import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.DeleteAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.GetAllAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.UpdateAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.account_use_case.UpdatePoinAccountUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.auth_use_case.SignInUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.auth_use_case.SignUpUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.DeleteEmployeeUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.GetAllEmployeeListUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.InsertEmployeeUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.employee_use_case.UpdateEmployeePoinUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.DeleteHistoryUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.GetAllHistoryUseCase
import com.malbyte.pointrecordingapp.feature_poin_recorder.domain.use_case.history_use_case.InsertHistoryUseCase

data class PoinRecordUseCases(
    val getAllEmployeeListUseCase: GetAllEmployeeListUseCase,
    val updateEmployeePoinUseCase: UpdateEmployeePoinUseCase,
    val insertEmployeeUseCase: InsertEmployeeUseCase,
    val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    val getAllHistoryUseCase: GetAllHistoryUseCase,
    val insertHistoryUseCase: InsertHistoryUseCase,
    val deleteHistoryUseCase: DeleteHistoryUseCase,
    val signUpUseCase: SignUpUseCase,
    val signInUseCase: SignInUseCase,
    val updateAccountUseCase: UpdateAccountUseCase,
    val getAllAccountUseCase: GetAllAccountUseCase,
    val deleteAccountUseCase: DeleteAccountUseCase,
    val updatePoinAccountUseCase: UpdatePoinAccountUseCase
)