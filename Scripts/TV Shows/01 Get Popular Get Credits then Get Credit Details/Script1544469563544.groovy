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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import theMovieDB.Credits
import theMovieDB.Movie
import theMovieDB.TVShow
import theMovieDB.TheMovieDBCommon as TheMovieDBCommon

response1 = TVShow.getPopular()

int TVID = TVShow.getRandomTV(response1)

if (TVID != -1) {
    response1 = TVShow.getCredits(TVID)
    TheMovieDBCommon.printDataValue(response1, 'TV/Get Credits')

    String creditID = TVShow.getRandomCredit(response1)

	if (creditID != null) {
		response1 = Credits.getDetails(creditID)
        TheMovieDBCommon.printDataValue(response1, 'Credit/Get Details')
    }else{
	println ("&&&&&&&&& No credits &&&&&&&&&&&&")
    }
}else{
	println ("&&&&&&&&& No popular Movie &&&&&&&&&&&&")
}


