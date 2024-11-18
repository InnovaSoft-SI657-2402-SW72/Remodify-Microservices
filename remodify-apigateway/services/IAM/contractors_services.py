from fastapi import APIRouter, Request, HTTPException
import requests
from configs.url_services import MICROSERVICE_IAM
from models.Contractors import ContractorsResource

contractors_router = APIRouter()

@contractors_router.get("/contractors/")
async def route_to_service_contractors(request: Request):
    url = f"{MICROSERVICE_IAM}/api/v1/contractors"
    response = requests.get(url, headers=request.headers)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@contractors_router.post("/contractors/")
async def route_to_service_contractors(request: Request, contractorsResource: ContractorsResource):
    url = f"{MICROSERVICE_IAM}/api/v1/contractors"
    response = requests.post(url, headers=request.headers, json=contractorsResource.dict())
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@contractors_router.get("/contractors/{contractorId}")
async def route_to_service_contractors(request: Request, contractorId: int):
    if contractorId <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_IAM}/api/v1/contractors/{contractorId}"
    response = requests.get(url, headers=request.headers)
    if response.status_code == 404:
        raise HTTPException(status_code=404, detail="ID not found")
    elif response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    
    return response.json()