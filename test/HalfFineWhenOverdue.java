
import java.util.Date;
import library.entities.Calendar;
import library.entities.ICalendar;
import library.entities.ILibrary;
import library.entities.ILoan;
import library.entities.helpers.BookHelper;
import library.entities.helpers.CalendarFileHelper;
import library.entities.helpers.LibraryFileHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Gauri..Chethana
 */
public class HalfFineWhenOverdue {
    
    private static LibraryFileHelper libraryHelper;
    private static ILibrary library;
    private ILoan currentLoan;
    static final double FINE_PER_DAY = 1.0;
    private static ICalendar calendar;
    private static CalendarFileHelper calendarHelper;

    @Before
    public void setUp() {
        calendarHelper = new CalendarFileHelper();
        calendar = calendarHelper.loadCalendar();
        libraryHelper = new LibraryFileHelper(new BookHelper(), new PatronHelper(), new LoanHelper());
        library = libraryHelper.loadLibrary();
        currentLoan = library.getCurrentLoanByBookId(1);
    }

    @Test
    public void calculateOverDueFineByOneDay() {
        double fine = 0.0;
        if (currentLoan.isOverDue()) {
            Date dueDate = currentLoan.getDueDate();
            calendar.setDate(dueDate);
            calendar.incrementDate(4);
            long daysOverDue = Calendar.getInstance().getDaysDifference(dueDate);
            fine = daysOverDue * FINE_PER_DAY;
            assertEquals(4.0, fine);
        }

    }

}
