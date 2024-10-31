from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_IAM
from models.Remodelers import RemodelersResource

remodelers_router = APIRouter()

@remodelers_router.post("/remodelers/")
async def route_to_service_remodelers(request: Request, remodelersResource: RemodelersResource):
    url = f"{MICROSERVICE_IAM}/remodelers"
    response = requests.post(url, headers=request.headers, json=remodelersResource.dict())
    return response.json()

@remodelers_router.get("/remodelers/{remodelerId}")
async def route_to_service_remodelers(request: Request, remodelerId: int):
    url = f"{MICROSERVICE_IAM}/remodelers/{remodelerId}"
    response = requests.get(url, headers=request.headers)
    return response.json()