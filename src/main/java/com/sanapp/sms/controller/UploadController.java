package com.sanapp.sms.controller;

import com.sanapp.sms.dto.Item;
import com.sanapp.sms.dto.ItemDetail;
import com.sanapp.sms.dto.UploadEditForm;
import com.sanapp.sms.services.IItemUploadService;
import com.sanapp.sms.services.IMeasurementService;
import com.sanapp.sms.utils.ExcelUtils;
import com.sanapp.sms.utils.SMSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Secured("ROLE_USER")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UploadController {

    private List<ItemDetail> itemDetails;
    private Map<Integer, ItemDetail> itemDetailsById;

    @Autowired
    private IItemUploadService iItemUploadService;

    @Autowired
    private IMeasurementService iMeasurementService;


    @GetMapping(value = {"/upload", "/saveinsystem"})
    public String uploadLandingPage(Model model) {
        if(iItemUploadService.showIsItemInSystemOrNot()){
            model.addAttribute("info", "No item Uploaded!");
        }
        return "upload";
    }


    @PostMapping("/excelupload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        try {
            itemDetails = ExcelUtils.parseExcelFile(file.getInputStream());
        } catch (Exception excelException) {
            model.addAttribute("error", "Uploaded Excel is not in correct format.");
            return "upload";
        }

        if (itemDetails != null) {
            itemDetailsById = itemDetails.stream().collect(Collectors.toMap(ItemDetail::getRowNumber, item -> item));
            String alreadyUploadedItems = iItemUploadService.excelUploadItems(itemDetails);
            if (!alreadyUploadedItems.isEmpty()) {
                model.addAttribute("warning", alreadyUploadedItems);
            }
            model.addAttribute("excelItemDetails", itemDetails);
        }

        return "upload";
    }

    private void clearItemDetailsData(List<ItemDetail> itemDetails) {
        if (itemDetails != null) {
            itemDetails.clear();
        }
    }

    @PostMapping("/saveinsystem")
    public String saveInSystem(Model model) throws IOException {
        try {
            if (itemDetails != null) {
                String alreadyUploadedItems = iItemUploadService.excelUploadItems(itemDetails);
                if (!alreadyUploadedItems.isEmpty()) {
                    model.addAttribute(SMSConstants.WARNING_KEY, SMSConstants.ALREADY_EXIST_WARNING);
                    clearItemDetailsData(itemDetails);
                } else {
                    iItemUploadService.uploadItems(itemDetails);
                    clearItemDetailsData(itemDetails);
                    model.addAttribute(SMSConstants.SUCCESS_KEY, SMSConstants.SAVED_SUCCESS_MSG);
                }

            } else {
                model.addAttribute(SMSConstants.INFO_KEY, SMSConstants.UPLOAD_WARNING_MSG);
            }
        } catch (DataAccessException exception) {
            model.addAttribute(SMSConstants.ERROR_KEY, "Uploaded data is not correct!");
            clearItemDetailsData(itemDetails);
        }

        return "upload";
    }

    @GetMapping(value = {"/editrecord"})
    public String editRecord(Model model, @RequestParam("rowId") int rowId) {
        if (itemDetails != null) {
            ItemDetail item = itemDetailsById.get(rowId);
            model.addAttribute("editItem", item);
        }

        return "editExcelRecord";
    }

    @PostMapping("/editmultirecord")
    public String editMultipleRecord(@ModelAttribute("test") UploadEditForm form,
                                     Model model) {

        String[] ids = form.getIds().split("_");
        List<ItemDetail> editedItemDetails = new ArrayList<>();
        if (itemDetails != null) {
            for (String id : ids) {

                ItemDetail item = itemDetailsById.get(Integer.valueOf(id));
                editedItemDetails.add(item);
            }

            model.addAttribute("editedItemDetails", editedItemDetails);
        }

        return "editExcelRecord";
    }

    @GetMapping("/addItemManual")
    public String addItemManually(Model model) {
        model.addAttribute("allMeasurements", iMeasurementService.listAllMeasurements());
        model.addAttribute("manualItemAdd", new Item());
        return "upload";
    }

}
