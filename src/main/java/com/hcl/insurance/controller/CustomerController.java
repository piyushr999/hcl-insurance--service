package com.hcl.insurance.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.dto.CustomerDto;
import com.hcl.insurance.entity.Customer;
import com.hcl.insurance.service.CustomerService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


@RestController
@RequestMapping("/polices")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService custService;
	
	
	@GetMapping("/customers/{id}")
	public void exportToPDF(@PathVariable("id") Long custId,final HttpServletRequest request, final HttpServletResponse response) throws IOException {
	    OutputStream os = null;
	    try {
	      System.out.println("::::::::::::::::::::::::::::::::::::::::      ");
	      CustomerDto data = custService.getCustomer(custId);
	      StringTemplate page = getStringTemplate();
	      page.setAttribute("data",data);
	      
	      String content = page.toString();

	      final HtmlCleaner htmlCleaner = new HtmlCleaner();
	      final TagNode tagNode = htmlCleaner.clean(content);
	      content = htmlCleaner.getInnerHtml(tagNode);

	      os = response.getOutputStream();

	      Document document = null;

	      document = new Document(PageSize.A4);

	      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      final PdfWriter writer = PdfWriter.getInstance(document, baos);
	      document.open();

	      final HtmlPipelineContext htmlContext = new HtmlPipelineContext();

	      htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

	      final CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
	      final Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext,
	          new PdfWriterPipeline(document, writer)));
	      final XMLWorker worker = new XMLWorker(pipeline, true);
	      final XMLParser parser = new XMLParser(worker);

	      try {
	        parser.parse(new StringReader(content));
	      } catch (final Exception e) {
	        e.printStackTrace();
	        
	      }

	      document.close();
	      response.setContentType("application/pdf");
	      response.addHeader(
	          "Content-Disposition",
	          "attachment; filename=customer_details.pdf");
	      System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::  "+baos.size());
	      response.setContentLength(baos.size());
	      baos.writeTo(os);
	    } catch (final IOException | DocumentException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (os != null) {
	          os.flush();
	          os.close();
	        }
	      } catch (final IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }

	  StringTemplate getStringTemplate() {
	    final StringTemplateGroup group = new StringTemplateGroup("Generators");
	    return group.getInstanceOf("customer_details");
	  }
	}	/*public ResponseEntity<InputStreamResource> getCustomer(@PathVariable("id") Long custId ) throws ApplicationException, IOException, DocumentException{
		logger.info("Received update product request.");
		
		   
		
	}*/


