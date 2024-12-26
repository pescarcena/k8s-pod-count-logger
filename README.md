# Running in Minikube

To run the application in Minikube, follow these steps:

## 1. Start Minikube

```sh
minikube start
```

## 2. Run the  script

```sh
./run-in-minikube.sh
```

The  script will:

- Load the Minikube Docker environment.
- Build the Docker image.
- Apply the deployment, role, and rolebinding over the `default` service account.

## 3. To see the app logs

```sh
kubectl logs -f -l app=pod-count
```

## 4. Scale the deployment

```sh   
kubectl scale deployment pod-count --replicas=3
```
