package f2.dsl.cqrs

import kotlin.js.JsExport
import kotlin.js.JsName


@JsExport
@JsName("Command")
interface Command: Message
