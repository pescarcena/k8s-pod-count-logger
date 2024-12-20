package com.pescarcena.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/pods")
public class PodCountControlller {
    private final KubernetesClient client;

    public PodCountControlller() {
        this.client = new KubernetesClientBuilder().build();
    }

    @GetMapping("/count")
    public int getPodCount() {
        try {
            // Get namespace from environment variable
            String namespace = System.getenv("KUBERNETES_NAMESPACE");
            String app_name = System.getenv("KUBERNETES_APP_NAME");
            
            List<Pod> pods = client.pods().inNamespace(namespace).withLabel("app", app_name).list().getItems();
            return pods.size();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching pod count: " + e.getMessage(), e);
        }
    }
}
