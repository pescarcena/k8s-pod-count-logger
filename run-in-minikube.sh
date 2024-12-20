# minikube start
# Load mini kube docker environment
eval $(minikube docker-env)  

# Build the docker image
docker build -t kubernetes-pod-count:0.0.1 .

# Apply the deployment, role and rolebinding
kubectl apply -f deployment.yaml
kubectl apply -f role.yaml
kubectl apply -f rolebinding.yaml
