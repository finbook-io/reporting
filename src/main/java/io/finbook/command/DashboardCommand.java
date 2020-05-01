package io.finbook.command;

import io.finbook.http.MyResponse;
import io.finbook.http.StandardResponse;
import io.finbook.model.Invoice;
import io.finbook.service.InvoiceService;
import io.finbook.sparkcontroller.ResponseCreator;
import io.finbook.util.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class DashboardCommand {

    private static InvoiceService invoiceService = new InvoiceService();

    public static ResponseCreator index(String currentUserId) {
        HashMap<String, Object> data = new HashMap<>();

        LocalDateTime startDate = Utils.getFirstDayCurrentMonth();
        LocalDateTime endDate = Utils.getCurrentDate();

        // Incomes of the current user
        List<Invoice> invoices = invoiceService.getAllInvoicesByIssuerIdPerPeriod(currentUserId, startDate, endDate);
        Double incomes = invoiceService.getSumTotalTaxes(invoices);
        data.put("incomes", Utils.formatDouble(incomes));

        // Refunds of the current user
        invoices = invoiceService.getAllInvoicesByReceiverIdPerPeriod(currentUserId, startDate, endDate);
        Double refunds = invoiceService.getSumTotalTaxes(invoices);
        data.put("refunds", Utils.formatDouble(refunds));

        // Total taxes due
        data.put("totalTaxesDue", Utils.formatDouble(incomes - refunds));

        return MyResponse.ok(
                new StandardResponse(data, "dashboard/index")
        );
    }

}
