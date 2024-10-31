from pydantic import BaseModel
from datetime import datetime

class ProjectRequestResource(BaseModel):
    name: str
    surname: str
    email: str
    phone: str
    address: str
    city: str
    summary: str
    businessId: int
    contractorId: int
    deadlineDate: datetime
    rooms: int
    budget: int