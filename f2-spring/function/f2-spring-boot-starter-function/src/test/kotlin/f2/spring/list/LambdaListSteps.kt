package f2.spring.list

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions

class LambdaListSteps: F2SpringStep() {

	init {
		prepareSteps()

		When("Execute ${LambdaList::functionList.name} with") { dataTable: DataTable ->
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaList::functionList.name) as (List<String>) -> List<String>
				bag.result[LambdaList::functionList.name] = functionPureKotlin(dataTable.asList())
			}
		}

		When("Execute ${LambdaList::supplierList.name}") {
			bag.contextBuilder.run { context ->
				val functionPureKotlin = context.getBean(LambdaList::supplierList.name) as () -> List<String>
				bag.result[LambdaList::supplierList.name] = functionPureKotlin()
			}
		}

		Then("The list result for {string} is") { value: String, dataTable: DataTable ->
			Assertions.assertThat(bag.result[value] as List<String>?).isEqualTo(dataTable.asList())
		}
	}

}