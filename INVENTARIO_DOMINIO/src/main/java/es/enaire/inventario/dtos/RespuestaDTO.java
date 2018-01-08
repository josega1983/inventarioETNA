package es.enaire.inventario.dtos;

/**
 * Clase generica que representa las respuestas de toda operacion del Backend.
 *
 */
public class RespuestaDTO  implements java.io.Serializable {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = -6136569414684477713L;

	/**
	 * Codigo de respuesta de la operacion del Backend.
	 */
	private java.lang.String codigoRespuesta;

	/**
	 * El listado de objetos de respuesta de la operacion del Backend.
	 */
    private java.lang.Object[] objetoRespuesta;


    /**
     * Constructor de la respuesta.
     */
    public RespuestaDTO() {
    }

    /**
     * Constructor de la respuesta.
     * @param codigoRespuesta El codigo de la respuesta.
     * @param objetoRespuesta El listado de objetos de la respuesta.
     */
    public RespuestaDTO(
           java.lang.String codigoRespuesta,
           java.lang.Object[] objetoRespuesta) {
           this.codigoRespuesta = codigoRespuesta;
           this.objetoRespuesta = objetoRespuesta;
    }


    /**
     * Obtiene el codigo de respuesta de la operacion del Backend.
     * @return codigoRespuesta El codigo de respuesta de la operacion del Backend.
     */
    public java.lang.String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Establece el codigo de respuesta de la operacion del Backend.
     * @param codigoRespuesta El codigo de respuesta de la operacion del Backend.
     */
    public void setCodigoRespuesta(java.lang.String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    /**
     * Obtiene el listado de objetos de respuesta de la operacion del Backend.
     * @return objetoRespuesta El listado de objetos de respuesta de la operacion del Backend.
     */
    public java.lang.Object[] getObjetoRespuesta() {
        return objetoRespuesta;
    }

    /**
     * Establece el listado de objetos de respuesta de la operacion del Backend.
     * @param objetoRespuesta El listado de objetos de respuesta de la operacion del Backend.
     */
    public void setObjetoRespuesta(java.lang.Object[] objetoRespuesta) {
        this.objetoRespuesta = objetoRespuesta;
    }


   //A PARTIR DE AQUI EL CODIGO FUE AUTOGENERADO.


    public java.lang.Object getObjetoRespuesta(int i) {
        return this.objetoRespuesta[i];
    }

    public void setObjetoRespuesta(int i, java.lang.Object _value) {
        this.objetoRespuesta[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    @SuppressWarnings("unused")
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaDTO)) return false;
        RespuestaDTO other = (RespuestaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.codigoRespuesta==null && other.getCodigoRespuesta()==null) ||
             (this.codigoRespuesta!=null &&
              this.codigoRespuesta.equals(other.getCodigoRespuesta()))) &&
            ((this.objetoRespuesta==null && other.getObjetoRespuesta()==null) ||
             (this.objetoRespuesta!=null &&
              java.util.Arrays.equals(this.objetoRespuesta, other.getObjetoRespuesta())));
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
        if (getCodigoRespuesta() != null) {
            _hashCode += getCodigoRespuesta().hashCode();
        }
        if (getObjetoRespuesta() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjetoRespuesta());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjetoRespuesta(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.es.aena.saeta.intra/", "respuestaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objetoRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
    @SuppressWarnings("rawtypes")
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
    @SuppressWarnings("rawtypes")
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType,
           java.lang.Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }
}
