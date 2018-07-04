# Disco Stack for Microservices
Discovery, Configuration, Traffic-shaping Stack that helps to perform dynamic routing to microservices based on multiple versions, service health, traffic-shaping, canary, A/B testing in production for existing Physical/VM environments/Container environments, with or without Kubernetes.

## Background
Enterprises today run a variety of services with a variety and mix of environments including physical servers, Virtual Machines, and now containers.  Services that interface with each other can exist across a mix of those environments.

Kubernetes offers several benefits that I will not outline in this note, but the upcoming Istio project offers multi-version deployments, traffic-shaping, canary testing, A/B testing etc.

This project gives a demonstration of how those benefits could be realized in a hybrid environment that incldues VMs, physical servers, Containers etc. without having to switch over to Kubernetes for the entire stack.

The reason behind this is because enterprises need time to transition all their systems to Containers and Container Orchestration environments like Kubernetes.

What can they use in the meantime to get similar benefits?  They can use the Disco stack to get similar benefits with their existing processes, existing deployments, using existing provisioning tools, existing CI/CD pipelines, existing production monitoring tools and processes.

## Description
The Disco stack uses Consul from Hashicorp for service and microservice based service discovery and registration, as a key-value pair configuration store, and uses Vault from Hashicorp for storing secrets, and Fabio from eBay as a reverse-proxy webserver, to route requests according to traffic shaping rules.  Please note that the Disco stack can also support etcd3 as a configuration store alternative.

### Service Registration and Discovery using Consul
Services including microservices, DBs, queueing systems, etc.  during startup register themselves with Consul, and can also opt retrieve their configuration from Consul.  This incldues Databases, and other infrastructure services.  This way, Consul acts as the single source of truth for the location of any service, and the location for which can be moved dynamically.

Services can be completely devoid of configuration or persisted state where they need to keep track of their dependencies, and can rely on using querying Consul to get that information as needed.

### Service Health
How can a service be checked for its health and be dynamically removed from the pool without expensive monitoring tools?  Consul provides the answer here as well.  Consul can be configured with custom health checks for each service and can remove their registration or mark it as unhealthy based on health check status.

### Multiple Versions of a service
Multiple versions of a service can register themselves within Consul, and can be routed to according to traffic-shaping rules in Fabio as explained below.

### Configuration using etcd3 or Consul
Services on startup can retrieve their configuration from Consul using a hierarchical namespace key identifying their compoonent within the namespace hierarchy.  

They can also watch for configuration changes.

This way, services can opt to be completely devoid of configuration and can retrieve and watch for configuration changes.

### Dynamic Routing based on versions, service health and Traffic-shaping for Canary, A/B testing
Kubernetes allows live and readiness checks, which allows for dynamic service request routing based on service health.

Istio, an upcoming Kubernetes sub-project also supports in addition to the above, traffic-shaping based on percentage of requests, which in turn allows for Canary testing and A/B testing, service deployment rollback etc.

How can we achieve similar benefits in a VM/Physical server environment while also working with Kubernetes or Container based deployments?

The Disco stack uses Fabio, a fast, modern, zero-conf load balancing HTTP(S) and TCP router for deploying applications managed by Consul.

Fabio allows services that are registered within Consul to routed to with Service requests. Also if a service health check fails, it queries Consul for healthy services and dynamically routes services only to those healthy services.

#### Traffic-shaping rules for dynamic routing of requests based on health, version etc.
Fabio also allows for %'s(percentages) of traffic to be routed to servies based on service health, service version(s) etc.  Fabio relies on dynamic rules that can be updated on the fly within Consul, and the router responds accordingly without having to reload configuration as in the case of consul-template which reloads Nginx and Apache configurations.

## Conclusion
This allows for similar benefits to Kubernetes and Istio to be realized by existing Hybrid VM/Physical server environments, thus giving existing enterprises a chance to enjoy similar benefits while transitioning to a completely Container Orchestrated, dynamic environment.


### Secrets
