from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_IAM
from models.Users import UsersResource

users_router = APIRouter()

@users_router.get("/users/")
async def route_to_service_users(request: Request):
    url = f"{MICROSERVICE_IAM}/users"
    response = requests.get(url, headers=request.headers)
    return response.json()

@users_router.get("/users/{userId}")
async def route_to_service_users(request: Request, userId: int):
    url = f"{MICROSERVICE_IAM}/users/{userId}"
    response = requests.get(url, headers=request.headers)
    return response.json()

@users_router.put("/users/{userId}")
async def route_to_service_users(request: Request, userId: int, usersResource: UsersResource):
    url = f"{MICROSERVICE_IAM}/users/{userId}"
    response = requests.put(url, headers=request.headers, json=usersResource.dict())
    return response.json()