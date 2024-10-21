from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_PROJECTREQUEST
from models.ProjectRequest import ProjectRequestResource

projectrequest_router = APIRouter()

@projectrequest_router.get("/project-requests/")
async def route_to_service_projectrequest(request: Request):
    url = f"{MICROSERVICE_PROJECTREQUEST}"
    response = requests.get(url, headers=request.headers)
    return response.json()

@projectrequest_router.post("/project-requests/")
async def route_to_service_projectrequest(request: Request, projectRequestResource: ProjectRequestResource):
    url = f"{MICROSERVICE_PROJECTREQUEST}"
    response = requests.post(url, headers=request.headers, json=projectRequestResource.dict())
    return response.json()

@projectrequest_router.get("/project-requests/{id}")
async def route_to_service_projectrequest(request: Request, id: int):
    url = f"{MICROSERVICE_PROJECTREQUEST}/{id}"
    response = requests.get(url, headers=request.headers)
    return response.json()