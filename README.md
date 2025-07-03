# Project Purpose
With over 9 years of experience as a software developer in a Chinese internet company, where most development relied on internal platforms and direct use of open-source tools was limited, I built this demo loan credit application system to combine my work experience with modern open-source frameworks and DevOps practices.

## Development stack
- Spring Boot
- MyBatis with MySQL
- Redis for distributed locking
- Swagger for API documentation

## Infrastructure (IaaS)
- Azure managed services for MySQL and Redis to reduce infrastructure maintenance
- Azure Web Service for deploying and hosting the application

## DevOps
- GitHub for code hosting
- CI/CD pipeline that builds and packages container images

## Future plans
- Implement automated Kubernetes deployment using Helm and Argo CD for multi-region disaster recovery
- Integrate Prometheus and Grafana for enhanced observability and monitoring

Additionally, this project serves as a portfolio piece to demonstrate my comprehensive understanding and experience in software engineering, aiming to support my pursuit of freelance opportunities.

# Play with this system
## 1. start mysql
```shell
brew services start mysql
brew services stop mysql

login on the terminal
  mysql -u root -p
```

## 2. start redis[optional]

## 3. start application
    com.loan.approve.ApprovalApplication.main

## 4. visit URL using swigger
```
http://localhost:9090/swagger-ui/index.html
```

# System Design
![creditApply.png](../../draw/creditApply.png)