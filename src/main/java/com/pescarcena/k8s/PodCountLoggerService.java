package com.pescarcena.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodCountLoggerService {
    private final KubernetesClient client;

    public PodCountLoggerService() {
        this.client = new KubernetesClientBuilder().build();
    }

    @Scheduled(fixedRate = 5000)
    public void logPodCount() {
        // Get namespace from environment variable
        String namespace = System.getenv("KUBERNETES_NAMESPACE");
        String app_name = System.getenv("KUBERNETES_APP_NAME");
        
        List<Pod> pods = client.pods().inNamespace(namespace).withLabel("app", app_name).list().getItems();
        int podCount = pods.size();
        System.out.println("Current pod count: " + podCount);
    }
}
