//Izvestaj za jednu fakturu po njenom id
@GetMapping("/{id}/izvestaj")
public ResponseEntity napraviIzvestaj(@PathVariable("id") long id){

        Faktura faktura=fakturaServiceInterface.findOne(id);

        if(faktura==null){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<StavkaFakture> stavkeFakture=stavkaFaktureServiceInterface.findByFaktura_id(faktura.getId());

        /* Convert the list above to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource stavkeFaktureJasper=new JRBeanCollectionDataSource(stavkeFakture);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> params=new HashMap<>();

        params.put("faktura",faktura);
        params.put("stavkeFakture",stavkeFakture);

        for(StavkaFakture sf:stavkeFakture){
        System.out.println(sf.getId());
        }

        try{

        /* Read jrxml file and creating JasperDesign object */
        InputStream is=new FileInputStream(new File("C:\\Users\\Rakitica\\JaspersoftWorkspace\\Fakture\\Faktura.jrxml"));

        JasperDesign jasperDesign=JRXmlLoader.load(is);

        /* Compiling jrxml with the help of JasperReport class */
        JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jp=JasperFillManager.fillReport(jasperReport,params,new JREmptyDataSource());
        ByteArrayInputStream bais=new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline; filename="+faktura.getBrojFakture()+"-"+faktura.getPoslovnaGodina().getGodina()+".pdf");
        return ResponseEntity
        .ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bais));
        }catch(Exception ex){
        ex.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }