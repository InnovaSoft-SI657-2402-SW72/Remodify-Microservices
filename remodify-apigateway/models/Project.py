from pydantic import BaseModel
from datetime import datetime

class ProjectResource(BaseModel):
    name: str
    description: str
    businessId: int
    contractorId: int
    startDate: datetime
    finishDate: datetime
    image: str