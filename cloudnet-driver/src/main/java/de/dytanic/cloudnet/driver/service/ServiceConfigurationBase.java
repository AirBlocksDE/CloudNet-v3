package de.dytanic.cloudnet.driver.service;

import de.dytanic.cloudnet.common.document.gson.BasicJsonDocPropertyable;
import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.serialization.ProtocolBuffer;
import de.dytanic.cloudnet.driver.serialization.SerializableObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceConfigurationBase extends BasicJsonDocPropertyable implements SerializableObject {

    protected Collection<ServiceRemoteInclusion> includes;

    protected Collection<ServiceTemplate> templates;

    protected Collection<ServiceDeployment> deployments;

    public ServiceConfigurationBase(Collection<ServiceRemoteInclusion> includes, Collection<ServiceTemplate> templates, Collection<ServiceDeployment> deployments) {
        this.includes = includes;
        this.templates = templates;
        this.deployments = deployments;
    }

    public ServiceConfigurationBase() {
    }

    public abstract Collection<String> getJvmOptions();

    public Collection<ServiceRemoteInclusion> getIncludes() {
        return this.includes;
    }

    public void setIncludes(Collection<ServiceRemoteInclusion> includes) {
        this.includes = includes;
    }

    public Collection<ServiceTemplate> getTemplates() {
        return this.templates;
    }

    public void setTemplates(Collection<ServiceTemplate> templates) {
        this.templates = templates;
    }

    public Collection<ServiceDeployment> getDeployments() {
        return this.deployments;
    }

    public void setDeployments(Collection<ServiceDeployment> deployments) {
        this.deployments = deployments;
    }

    @Override
    public void write(ProtocolBuffer buffer) {
        buffer.writeObjectCollection(this.includes);
        buffer.writeObjectCollection(this.templates);
        buffer.writeObjectCollection(this.deployments);
        buffer.writeString(super.properties.toJson());
    }

    @Override
    public void read(ProtocolBuffer buffer) {
        this.includes = buffer.readObjectCollection(ServiceRemoteInclusion.class);
        this.templates = buffer.readObjectCollection(ServiceTemplate.class);
        this.deployments = buffer.readObjectCollection(ServiceDeployment.class);
        super.properties = JsonDocument.newDocument(buffer.readString());
    }
}