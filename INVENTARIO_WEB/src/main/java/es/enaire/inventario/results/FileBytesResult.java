package es.enaire.inventario.results;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

import es.enaire.inventario.actions.VisualizarArchivoAction;
import es.enaire.inventario.util.Utilidades;

public class FileBytesResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1064776094021782606L;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		VisualizarArchivoAction visualizarArchivoAction =(VisualizarArchivoAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if(visualizarArchivoAction.getInputStream() != null) {
			response.setContentType("application/pdf");
			response.getOutputStream().write(IOUtils.toByteArray(visualizarArchivoAction.getInputStream()));
		}
		else {
			ServletContext contexto = ServletActionContext.getServletContext();
			response.setContentType("png");
			response.getOutputStream().write(Utilidades.convertFileToBytes(new File(contexto.getRealPath("/jsp/common/menuRight/popup/Manual.pdf"))));
		}
		response.getOutputStream().flush();

	}

}
