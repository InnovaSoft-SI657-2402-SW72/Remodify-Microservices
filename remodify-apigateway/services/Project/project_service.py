from fastapi import APIRouter, Request, HTTPException
import requests
from configs.url_services import MICROSERVICE_PROJECT
from models.Project import ProjectResource

project_router = APIRouter()

@project_router.get("/projects/")
async def route_to_service_project(request: Request):
    url = f"{MICROSERVICE_PROJECT}"
    response = requests.get(url, headers=request.headers)
    return response.json()

@project_router.post("/projects/")
async def route_to_service_project(request: Request, projectResource: ProjectResource):
    url = f"{MICROSERVICE_PROJECT}"
    response = requests.post(url, headers=request.headers, json=projectResource.dict())
    return response.json()

@project_router.get("/projects/{id}")
async def route_to_service_project(request: Request, id: int):
    if id <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_PROJECT}/{id}"
    response = requests.get(url, headers=request.headers)
    if response.status_code == 404:
        raise HTTPException(status_code=404, detail="ID not found")
    
    return response.json()

@project_router.get("/projects/search/")
async def route_to_service_project(request: Request, query: str):
    url = f"{MICROSERVICE_PROJECT}/search"
    params = {"query": query}
    response = requests.get(url, headers=request.headers, params=params)
    return response.json()