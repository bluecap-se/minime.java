[
  {
    "name": "app",
    "image": "${docker_image_url_django}",
    "essential": true,
    "cpu": 10,
    "memory": 512,
    "links": [],
    "portMappings": [
      {
        "containerPort": 8080,
        "hostPort": 0,
        "protocol": "tcp"
      }
    ],
    "command": ["runproduction"],
    "environment": [
      {
        "name": "SPRING_APPLICATION_JSON",
        "value": "{\"spring.datasource.url\": \"jdbc:postgresql://${rds_username}:${rds_password}@${rds_hostname}:5432/${rds_db_name}\"}"
      },
    ],
    "logConfiguration": {
      "logDriver": "awslogs",
      "options": {
        "awslogs-group": "/ecs/app",
        "awslogs-region": "${region}",
        "awslogs-stream-prefix": "app-log-stream"
      }
    }
  },
  {
    "name": "nginx",
    "image": "${docker_image_url_nginx}",
    "essential": true,
    "cpu": 10,
    "memory": 128,
    "links": ["app"],
    "portMappings": [
      {
        "containerPort": 80,
        "hostPort": 0,
        "protocol": "tcp"
      }
    ],
    "logConfiguration": {
      "logDriver": "awslogs",
      "options": {
        "awslogs-group": "/ecs/nginx",
        "awslogs-region": "${region}",
        "awslogs-stream-prefix": "nginx-log-stream"
      }
    }
  }
]
