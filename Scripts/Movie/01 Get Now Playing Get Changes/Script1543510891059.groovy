import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import theMovieDB.TheMovieDBCommon


response1 = WS.sendRequest(findTestObject('Movies/Get Now Playing'))

def slurper = new groovy.json.JsonSlurper()

def dataValue = slurper.parseText(response1.getResponseBodyContent())

int resultSize = dataValue.results.size() - 1

if (resultSize >= 0) {
    int i = ((Math.random() * resultSize) as int)

	RequestObject reqObj = findTestObject('Movies/Get Changes')
	String urlStr = "https://api.themoviedb.org/3/movie/" + dataValue.results[i].id + "/changes"
	reqObj.setRestUrl(urlStr)
    println('************* URL: ' + urlStr)
	
	List<TestObjectProperty> params = new ArrayList()
	params.add(new TestObjectProperty("api_key",ConditionType.EQUALS, GlobalVariable.apiKey))
	reqObj.setRestParameters(params)
	
	response1 = WS.sendRequest(reqObj)
	TheMovieDBCommon.printDataValue(response1, 'Movies/Get Changes')
}



