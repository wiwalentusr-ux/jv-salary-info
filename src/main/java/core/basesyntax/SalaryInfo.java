package core.basesyntax;

public class SalaryInfo {
    public static final int DATE_COLUMN_INDEX = 0;
    public static final int NAME_COLUMN_INDEX = 1;
    public static final int HOUR_COLUMN_INDEX = 2;
    public static final int PRICE_COLUMN_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int dateFromSize = dateSize(dateFrom);
        int dateToSize = dateSize(dateTo);

        StringBuilder reportStringBuilder = new StringBuilder();
        reportStringBuilder
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int personalSalary = 0;

            for (String salaryInfoColumn : data) {
                String[] splitInfo = salaryInfoColumn.split(" ");
                String salaryDate = splitInfo[DATE_COLUMN_INDEX];
                int salaryDateSize = dateSize(salaryDate);

                if (!name.equals(splitInfo[NAME_COLUMN_INDEX])
                        || dateFromSize >= salaryDateSize || dateToSize < salaryDateSize) {
                    continue;
                }

                int salaryHour = Integer.valueOf(splitInfo[HOUR_COLUMN_INDEX]);
                int salaryPrice = Integer.valueOf(splitInfo[PRICE_COLUMN_INDEX]);

                personalSalary += salaryHour * salaryPrice;
            }

            reportStringBuilder
                    .append("\n")
                    .append(name)
                    .append(" - ")
                    .append(personalSalary);
        }
        return reportStringBuilder.toString();
    }

    public int dateSize(String dataString) {
        String[] splitDateArray = dataString.split("\\.");
        StringBuilder dateBuilder = new StringBuilder();
        for (String splitDate : splitDateArray) {
            dateBuilder.insert(0, splitDate);
        }
        return Integer.valueOf(dateBuilder.toString());
    }

}
