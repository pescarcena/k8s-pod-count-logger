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
        List<Pod> pods = client.pods().inNamespace("default").withLabel("app", "pod-count").list().getItems();
        int podCount = pods.size();
        System.out.println("Current pod count: " + podCount);
    }
}
