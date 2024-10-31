from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_IAM
from models.Contractors import ContractorsResource

contractors_router = APIRouter()

@contractors_router.get("/Contractors/")
async def route_to_service_contractors(request: Request):
    url = f"{MICROSERVICE_IAM}/Contractors"
    response = requests.get(url, headers=request.headers)
    return response.json()

@contractors_router.post("/Contractors/")
async def route_to_service_contractors(request: Request, contractorsResource: ContractorsResource):
    url = f"{MICROSERVICE_IAM}/Contractors"
    response = requests.post(url, headers=request.headers, json=contractorsResource.dict())
    return response.json()

@contractors_router.get("/Contractors/{contractorId}")
async def route_to_service_contractors(request: Request, contractorId: int):
    url = f"{MICROSERVICE_IAM}/Contractors/{contractorId}"
    response = requests.get(url, headers=request.headers)
    return response.json()
