import dao.ReportDAO;

public class Main {
    public static void main(String[] args) {

        ReportDAO reportDAO = new ReportDAO();
        reportDAO.getEquipmentUtilizationReport(1);
    }
}
