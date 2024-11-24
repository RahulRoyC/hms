package com.hms.service;

import com.hms.entity.Booking;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PDFService {
    // Implement the functionality to generate PDFs here



    public void generateBookingPDF(Booking booking) {
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("E:\\Bookings\\booking" + booking.getId() + ".pdf"));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        doc.open();

        // Add logo
        Image logo = null;
        try {
            logo = Image.getInstance("D:\\ItelliJ IDEA WorkSpace\\HotelManagementSystem\\src\\main\\resources\\logo.png");
            logo.scaleAbsolute(100, 50);
            logo.setAbsolutePosition((PageSize.A4.getWidth() - logo.getScaledWidth()) / 2, 750);
            doc.add(logo);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        // Add some space
        try {
            doc.add(new Paragraph("\n\n"));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        // Add header
        Paragraph header = new Paragraph("Booking Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        header.setAlignment(Element.ALIGN_CENTER);
        try {
            doc.add(header);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // Add some space
        try {
            doc.add(new Paragraph("\n\n"));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        // Add booking details table
        PdfPTable table = new PdfPTable(6);
        addTableHeader(table);
        addRows(table, booking);
        try {
            doc.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // Add some space
        try {
            doc.add(new Paragraph("\n\n"));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        // Add footer
        Paragraph footer = new Paragraph("Thank you for choosing our hotel!", FontFactory.getFont(FontFactory.HELVETICA, 14));
        footer.setAlignment(Element.ALIGN_CENTER);
        try {
            doc.add(footer);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Guest Name", "Room Type", "Property Name", "Check In", "Check Out", "Total Price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, Booking booking) {
        table.addCell(booking.getGuestName());
        table.addCell(booking.getType());
        table.addCell(booking.getProperty().getName());
        table.addCell(booking.getCheckInDate().toString());
        table.addCell(booking.getCheckOutDate().toString());
        table.addCell(String.valueOf(booking.getTotalPrice()));
    }



}
