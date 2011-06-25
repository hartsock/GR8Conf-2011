package date;

import java.util.Calendar as C

public class DateDSL {
  static months = [ 'january':C.JANUARY, 'february':C.FEBRUARY, 'march':C.MARCH, 'april':C.APRIL, 'may':C.MAY, 'june':C.JUNE, 'july':C.JULY, 'august':C.AUGUST, 'september':C.SEPTEMBER, 'october':C.OCTOBER, 'november':C.NOVEMBER, 'december':C.DECEMBER ]
  static days = [ 'sunday':C.SUNDAY, 'monday':C.MONDAY, 'tuesday':C.TUESDAY, 'wednesday':C.WEDNESDAY, 'thursday':C.THURSDAY, 'friday':C.FRIDAY, 'saturday':C.SATURDAY ]

  def state = [:]

  

  def propertyMissing(String name) {
    if(name == "every") {
      this.state.every = true
      return this
    }
    if ( days.containsKey(name) ) {
      this.state.day = days[name]
      return this
    }
  }

  def methodMissing(String name, args) {
    if(name == "in" ) {
      in0(args)
    }
  }

  def in0(args) {
    List days = []

    args.each { int year ->
      if(state.every) {
        Calendar cal = new GregorianCalendar();
        cal.set(C.YEAR,year)
        cal.set(C.MONTH,0)
        cal.set(C.DAY_OF_YEAR,1)
        while( cal.get(C.YEAR) == year ){
          if(cal.get(C.DAY_OF_WEEK) == state.day) {
            days.add(cal.getTime())
          }
          cal.add(C.DAY_OF_YEAR, 1)
        }
      }
    }

    return days
  }

  static interpret(String code) {
    def dsl = new DateDSL()
    def shell = new GroovyShell()
    Closure closure = shell.evaluate("{-> ${code} }")
    closure.delegate = dsl
    closure.call()
  }
}
