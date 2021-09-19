package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.ErrorDTO
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("R2Exception")
open class S2Exception(
	val id: String,
	val error: ErrorDTO<*>,
) : Exception(error.description)
