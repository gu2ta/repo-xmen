package ar.com.ml.xmen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.ml.xmen.beans.DnaSequenceTest;
import ar.com.ml.xmen.controller.HomeControllerTest;
import ar.com.ml.xmen.controller.RecruitmentControllerTest;
import ar.com.ml.xmen.service.RecruitmentServiceTest;
import ar.com.ml.xmen.utils.encrypter.EncrypterTest;

@RunWith(Suite.class)
@SuiteClasses({ DnaSequenceTest.class, HomeControllerTest.class, RecruitmentServiceTest.class, RecruitmentControllerTest.class, EncrypterTest.class})
public class ApplicationTestSuite {

}
