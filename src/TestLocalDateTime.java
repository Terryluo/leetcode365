import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.*;

public class TestLocalDateTime {
    public static void main(String[] args) {
        TestLocalDateTime testLocalDateTime = new TestLocalDateTime();
        //testLocalDateTime.printAllDateTime();
        testLocalDateTime.dateTimeCheck3();
    }

    public void dateTimeCheck1() {
        // Original date string
        String dateStr = "2024-05-31";

        // Define the locales
        Locale usLocale = Locale.US;
        Locale australiaLocale = new Locale("en", "AU");
        Locale chinaLocale = Locale.CHINA;

        // Parse the date string to LocalDate
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, inputFormatter);

        DateTimeFormatter usFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd", usLocale);

        System.out.println(date.format(usFormatter2));

        // Format the date for US English
        DateTimeFormatter usFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(usLocale);
        String usFormattedDate = date.format(usFormatter);

        // Format the date for Australian English
        DateTimeFormatter australiaFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(australiaLocale);
        String australiaFormattedDate = date.format(australiaFormatter);

        // Format the date for Chinese
        DateTimeFormatter chinaFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(chinaLocale);
        String chinaFormattedDate = date.format(chinaFormatter);

        // Print the formatted dates
        System.out.println("US formatted date: " + usFormattedDate);
        System.out.println("Australian formatted date: " + australiaFormattedDate);
        System.out.println("Chinese formatted date: " + chinaFormattedDate);
    }

    public void dateTimeCheck2() {
        // Get the current date and time as LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();

        // Define the locales
        Locale usLocale = Locale.US;
        Locale franceLocale = Locale.FRANCE;
        Locale italyLocale = Locale.ITALY;
        Locale dutchLocale = new Locale("nl", "NL");
        Locale spanishLocale = new Locale("es", "ES");
        Locale germanLocale = Locale.GERMANY;

        // Format the datetime for US English using a localized datetime formatter
        DateTimeFormatter usFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(usLocale);
        String usFormattedDateTime = dateTime.format(usFormatter);

        // Format the datetime for French
        DateTimeFormatter franceFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(franceLocale);
        String franceFormattedDateTime = dateTime.format(franceFormatter);

        // Format the datetime for Italian
        DateTimeFormatter italyFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(italyLocale);
        String italyFormattedDateTime = dateTime.format(italyFormatter);

        // Format the datetime for Dutch
        DateTimeFormatter dutchFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(dutchLocale);
        String dutchFormattedDateTime = dateTime.format(dutchFormatter);

        // Format the datetime for Spanish
        DateTimeFormatter spanishFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(spanishLocale);
        String spanishFormattedDateTime = dateTime.format(spanishFormatter);

        // Format the datetime for German
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(germanLocale);
        String germanFormattedDateTime = dateTime.format(germanFormatter);

        // Print the formatted datetimes
        System.out.println("US formatted datetime: " + usFormattedDateTime);
        System.out.println("French formatted datetime: " + franceFormattedDateTime);
        System.out.println("Italian formatted datetime: " + italyFormattedDateTime);
        System.out.println("Dutch formatted datetime: " + dutchFormattedDateTime);
        System.out.println("Spanish formatted datetime: " + spanishFormattedDateTime);
        System.out.println("German formatted datetime: " + germanFormattedDateTime);
    }

    public void printAllDateTime() {
        final java.util.Date date = new Date();
        List<Locale> localesList = new ArrayList<>();
        localesList.add(Locale.US);
        localesList.add(Locale.FRANCE);
        localesList.add(Locale.GERMAN);
        localesList.add(new Locale("es", "Es"));
        localesList.add(Locale.ITALIAN);

        Arrays.stream(java.text.DateFormat.getAvailableLocales())
                .sorted(Comparator.comparing(Locale::getDisplayName))
                .forEachOrdered(locale -> {
                    for (Locale language : localesList) {
                        if (locale.equals(language)) {
                            java.text.DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);
                            System.out.format("%-20s, %s\n", df.format(date), locale.getDisplayName(Locale.US));
                            System.out.format("%-10s, %s\n", df.format(date), locale.getDisplayName(Locale.US));
                            System.out.format("%s, %s\n", df.format(date), locale.getDisplayName(Locale.US));
                        }
                    }
                });
    }
    public void dateTimeCheck3() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US)
                .withZone(ZoneId.systemDefault());
        System.out.println(dateTime.format(formatter));
        System.out.println(dateTime.getZone().getDisplayName(TextStyle.SHORT, Locale.US));
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("z")));
    }
}
