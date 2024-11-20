from fastapi import APIRouter, Request, HTTPException
import requests
from configs.url_services import MICROSERVICE_PROJECT_REQUEST
from models.ProjectRequest import ProjectRequestResource

project_request_router = APIRouter()

@project_request_router.get("/project-requests/")
async def route_to_service_project_request(request: Request):
    url = f"{MICROSERVICE_PROJECT_REQUEST}/api/v1/project-requests"
    response = requests.get(url, headers=request.headers)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@project_request_router.post("/project-requests/")
async def route_to_service_project_request(request: Request, projectRequestResource: ProjectRequestResource):
    url = f"{MICROSERVICE_PROJECT_REQUEST}"
    response = requests.post(url, headers=request.headers, json=projectRequestResource.dict())
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@project_request_router.get("/project-requests/{id}")
async def route_to_service_project_request(request: Request, id: int):
    if id <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_PROJECT_REQUEST}/{id}"
    response = requests.get(url, headers=request.headers)
    if response.status_code == 404:
        raise HTTPException(status_code=404, detail="ID not found")
    elif response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    
    return response.json()

@project_request_router.get("/project-requests/search/")
async def route_to_service_project_request(request: Request, businessId: int = None, contractorId: int = None):
    url = f"{MICROSERVICE_PROJECT_REQUEST}/search"
    params = {}
    if businessId is not None:
        params["businessId"] = businessId
    if contractorId is not None:
        params["contractorId"] = contractorId
    response = requests.get(url, headers=request.headers, params=params)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()