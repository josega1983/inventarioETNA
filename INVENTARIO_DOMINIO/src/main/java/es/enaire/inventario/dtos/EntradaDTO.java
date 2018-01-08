/**
 * EntradaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.enaire.inventario.dtos;

public class EntradaDTO  implements java.io.Serializable {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = -7439230490124865999L;
	/**
	 *  Objeto de entrada a: crear, actualizar o eliminar de la operacion del Backend.
	 */
	private java.lang.Object objetoEntrada;
	/**
	 * En caso de listados, pagina actual de la operacion del Backend.
	 */
    private java.lang.Long paginaActual;
	/**
	 * En caso de listados, tamanio de pagina de la operacion del Backend.
	 */
    private java.lang.Long tamanioPagina;
	/**
	 * Tipo de entrada de la operacion del Backend.
	 */
    private java.lang.String tipoEntrada;
    /**
     * Accion en que se encuentra
     */
    private java.lang.Integer accion;
    /**
     * Constructor de la entrada
     */
    public EntradaDTO() {
    }
    /**
     * Constructor de la respuesta.
     * @param objetoEntrada objeto de entrada
     * @param paginaActual pagina actual
     * @param tamanioPagina tamanio de pagina
     * @param tipoEntrada tipo de entrada
     * @param accion accion
     */

    public EntradaDTO(
           java.lang.Object objetoEntrada,
           java.lang.Long paginaActual,
           java.lang.Long tamanioPagina,
           java.lang.String tipoEntrada,
           java.lang.Integer accion) {
           this.objetoEntrada = objetoEntrada;
           this.paginaActual = paginaActual;
           this.tamanioPagina = tamanioPagina;
           this.tipoEntrada = tipoEntrada;
           this.accion = accion;
    }


    /**
     *  Obtiene el objeto de entrada a: crear, actualizar o eliminar de la operacion del Backend.
     *
     * @return objetoEntrada el objeto de entrada a: crear, actualizar o eliminar de la operacion del Backend.
     */
    public java.lang.Object getObjetoEntrada() {
        return objetoEntrada;
    }


    /**
     * Establece el objeto de entrada a: crear, actualizar o eliminar de la operacion del Backend.
     *
     * @param objetoEntrada el objeto de entrada a: crear, actualizar o eliminar de la operacion del Backend.
     */
    public void setObjetoEntrada(java.lang.Object objetoEntrada) {
        this.objetoEntrada = objetoEntrada;
    }


    /**
     * Obtiene la pagina actual de la operacion del Backend.
     *
     * @return paginaActual la pagina actual de la operacion del Backend.
     */
    public java.lang.Long getPaginaActual() {
        return paginaActual;
    }


    /**
     * Establece la pagina actual de la operacion del Backend..
     *
     * @param paginaActual la pagina actual de la operacion del Backend.
     */
    public void setPaginaActual(java.lang.Long paginaActual) {
        this.paginaActual = paginaActual;
    }


    /**
     * Obtiene el tamanio de pagina de la operacion del Backend.
     *
     * @return tamanioPagina el tamanio de pagina de la operacion del Backend.
     */
    public java.lang.Long getTamanioPagina() {
        return tamanioPagina;
    }


    /**
     * Establece el tamanio de pagina de la operacion del Backend.
     *
     * @param tamanioPagina el tamanio de pagina de la operacion del Backend.
     */
    public void setTamanioPagina(java.lang.Long tamanioPagina) {
        this.tamanioPagina = tamanioPagina;
    }


    /**
     * Obtiene el tipo de entrada de la operacion del Backend.
     *
     * @return tipoEntrada el tipo de entrada de la operacion del Backend.
     */
    public java.lang.String getTipoEntrada() {
        return tipoEntrada;
    }


    /**
     * Establece el tipo de entrada de la operacion del Backend.
     *
     * @param tipoEntrada el tipo de entrada de la operacion del Backend.
     */
    public void setTipoEntrada(java.lang.String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }
    /**
     * Obtiene la accion en que se encuentra
     * @return la accion en que se encuentra
     */
	public java.lang.Integer getAccion() {
		return accion;
	}
	/**
	 * Establece la accion en que se encuentra
	 * @param accion la accion en que se encuentra
	 */
	public void setAccion(java.lang.Integer accion) {
		this.accion = accion;
	}

	//A PARTIR DE AQUI EL CODIGO FUE AUTOGENERADO.
    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntradaDTO)) return false;
        EntradaDTO other = (EntradaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.objetoEntrada==null && other.getObjetoEntrada()==null) ||
             (this.objetoEntrada!=null &&
              this.objetoEntrada.equals(other.getObjetoEntrada()))) &&
            ((this.paginaActual==null && other.getPaginaActual()==null) ||
             (this.paginaActual!=null &&
              this.paginaActual.equals(other.getPaginaActual()))) &&
            ((this.tamanioPagina==null && other.getTamanioPagina()==null) ||
             (this.tamanioPagina!=null &&
              this.tamanioPagina.equals(other.getTamanioPagina()))) &&
            ((this.accion==null && other.getAccion()==null) ||
               (this.accion!=null &&
                this.accion.equals(other.getAccion()))) &&
            ((this.tipoEntrada==null && other.getTipoEntrada()==null) ||
             (this.tipoEntrada!=null &&
              this.tipoEntrada.equals(other.getTipoEntrada())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getObjetoEntrada() != null) {
            _hashCode += getObjetoEntrada().hashCode();
        }
        if (getPaginaActual() != null) {
            _hashCode += getPaginaActual().hashCode();
        }
        if (getTamanioPagina() != null) {
            _hashCode += getTamanioPagina().hashCode();
        }
        if (getAccion() != null) {
        	_hashCode += getAccion().hashCode();
        }
        if (getTipoEntrada() != null) {
            _hashCode += getTipoEntrada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntradaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.es.aena.saeta.intra/", "entradaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objetoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginaActual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paginaActual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanioPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tamanioPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }
}
