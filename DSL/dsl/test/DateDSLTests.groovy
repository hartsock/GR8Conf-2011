import date.DateDSL;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Shawn
 *
 * def wednesdays = dateDsl.every.wednesday.in(2009)
 *
 */
public class DateDSLTests extends GroovyTestCase {
  void testEveryWednesdayIn2009Year() {
    def dsl = new DateDSL();
    assert dsl != null

    assert dsl.every != null
    assert dsl.every.wednesday != null
    
    List list = dsl.every.wednesday.in(2009)
    
    assert list != null
    assert list.size() > 0
    assert list.size() < 53
    assert list.size() > 51;
    def lastDate
    list.each {
      if(lastDate) {
        assert it > lastDate
      }
      lastDate = it
    }

    def orderedList = list.clone().sort()

    assert !orderedList.is(list)
  }



  void testExternalDSL() {
    String dslCode = "every.wednesday.in 2009"

    def list = DateDSL.interpret(dslCode)

    assert list != null
    assert list.size() > 0
    assert list.size() < 53
    assert list.size() > 51;
    def lastDate
    list.each {
      if(lastDate) {
        assert it > lastDate
      }
      lastDate = it
    }

    def orderedList = list.clone().sort()

    assert !orderedList.is(list)
  } //*/

}
