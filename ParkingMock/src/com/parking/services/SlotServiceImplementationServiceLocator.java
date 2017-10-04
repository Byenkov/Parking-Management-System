/**
 * SlotServiceImplementationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.parking.services;

public class SlotServiceImplementationServiceLocator extends org.apache.axis.client.Service implements com.parking.services.SlotServiceImplementationService {

    public SlotServiceImplementationServiceLocator() {
    }


    public SlotServiceImplementationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SlotServiceImplementationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SlotServiceImplementation
    private java.lang.String SlotServiceImplementation_address = "http://localhost:8092/ParkProject/services/SlotServiceImplementation";

    public java.lang.String getSlotServiceImplementationAddress() {
        return SlotServiceImplementation_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SlotServiceImplementationWSDDServiceName = "SlotServiceImplementation";

    public java.lang.String getSlotServiceImplementationWSDDServiceName() {
        return SlotServiceImplementationWSDDServiceName;
    }

    public void setSlotServiceImplementationWSDDServiceName(java.lang.String name) {
        SlotServiceImplementationWSDDServiceName = name;
    }

    public com.parking.services.SlotServiceImplementation getSlotServiceImplementation() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SlotServiceImplementation_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSlotServiceImplementation(endpoint);
    }

    public com.parking.services.SlotServiceImplementation getSlotServiceImplementation(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.parking.services.SlotServiceImplementationSoapBindingStub _stub = new com.parking.services.SlotServiceImplementationSoapBindingStub(portAddress, this);
            _stub.setPortName(getSlotServiceImplementationWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSlotServiceImplementationEndpointAddress(java.lang.String address) {
        SlotServiceImplementation_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.parking.services.SlotServiceImplementation.class.isAssignableFrom(serviceEndpointInterface)) {
                com.parking.services.SlotServiceImplementationSoapBindingStub _stub = new com.parking.services.SlotServiceImplementationSoapBindingStub(new java.net.URL(SlotServiceImplementation_address), this);
                _stub.setPortName(getSlotServiceImplementationWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SlotServiceImplementation".equals(inputPortName)) {
            return getSlotServiceImplementation();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services.parking.com", "SlotServiceImplementationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services.parking.com", "SlotServiceImplementation"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SlotServiceImplementation".equals(portName)) {
            setSlotServiceImplementationEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
