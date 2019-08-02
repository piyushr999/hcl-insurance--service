package com.hcl.insurance.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.insurance.dto.CustomerDto;
import com.hcl.insurance.dto.PolicyDto;
import com.hcl.insurance.exception.ApplicationException;
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

	@Autowired
	CustomerService custService;

	private String startTr = "<tr>";
	private String endTr = "</tr>";
	private String startTD = " <td colspan='2' style='padding: .1pt .1pt .1pt .1pt; width : 100%'><span style='font-size:10.0pt;font-family:Arial;'>";
	private String endTD = "</span></td>";

	@GetMapping("/customers/{id}")
	public void exportToPDF(@PathVariable("id") Long custId, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, ApplicationException {
		OutputStream os = null;
		try {
			CustomerDto data = custService.getCustomer(custId);
			List<PolicyDto> policiesDtoList = data.getPolicies();
			String policies = "";
			if (null != policiesDtoList) {
				policies = generateHTML(policiesDtoList);
			}
			StringTemplate page = getStringTemplate();
			page.setAttribute("data", data);
			page.setAttribute("policies", policies);

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
			final Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
					new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer)));
			final XMLWorker worker = new XMLWorker(pipeline, true);
			final XMLParser parser = new XMLParser(worker);

			try {
				parser.parse(new StringReader(content));
			} catch (final Exception e) {
				e.printStackTrace();

			}

			document.close();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=customer_details.pdf");
			System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::  " + baos.size());
			response.setContentLength(baos.size());
			baos.writeTo(os);
		} catch (final IOException | DocumentException e) {
			throw new ApplicationException("Unknow system error");
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (final IOException e) {
				throw new ApplicationException("Unknow system error");
			}
		}
	}

	StringTemplate getStringTemplate() {
		final StringTemplateGroup group = new StringTemplateGroup("Generators");
		return group.getInstanceOf("customer_details");
	}

	private String generateHTML(List<PolicyDto> policiesDtoList) {
		String policies = startTr + startTD + endTD + endTr;
		StringBuilder rows = new StringBuilder("");
		rows.append(startTr);
		rows.append(startTD + "Policy Number" + endTD);
		rows.append(startTD + "Policy Name" + endTD);
		rows.append(startTD + "Policy Baseamount" + endTD);
		rows.append(startTD + "Policy Description" + endTD);
		rows.append(endTr);
		for (PolicyDto p : policiesDtoList) {
			rows.append(startTr);
			rows.append(startTD + p.getPolicyId() + endTD);
			rows.append(startTD + p.getPolicyName() + endTD);
			rows.append(startTD + p.getPolicyBaseAmount() + endTD);
			rows.append(startTD + p.getPolicyDescription() + endTD);
			rows.append(endTr);
		}
		return rows.toString();
	}
}
