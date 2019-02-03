package ar.com.ml.xmen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.ml.xmen.controller.RecruitmentControllerTest;
import ar.com.ml.xmen.service.RecruitmentServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ RecruitmentServiceTest.class, RecruitmentControllerTest.class })
public class ApplicationTestSuite {

}
