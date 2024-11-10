import faiss
import numpy as np
from langchain_community.embeddings import OpenAIEmbeddings

class VectorDatabase:
    def __init__(self, embedding_dimension: int = 1536):
        self.embedding_dimension = embedding_dimension
        self.index = faiss.IndexFlatL2(embedding_dimension)  # Usa FAISS con distancia L2 (euclidiana)
        self.embedding_model = OpenAIEmbeddings()
        self.documents = []  # Lista para almacenar documentos junto con sus IDs

    def upsert_document(self, doc_id: str, text: str):
        # Genera el embedding del texto
        embedding = self.embedding_model.embed_query(text)
        embedding_np = np.array([embedding], dtype='float32')  # Convierte a un array numpy
        self.index.add(embedding_np)  # Añade el embedding al índice
        self.documents.append((doc_id, text))  # Guarda el documento con su ID

    def retrieve_documents(self, query: str, top_k: int = 5):
        # Si no hay documentos en la base de datos, devolver una lista vacía
        if not self.documents:
            print("No hay documentos en la base de datos vectorial.")
            return []

        # Obtener el embedding de la consulta
        query_embedding = self.embedding_model.embed_query(query)
        query_embedding_np = np.array([query_embedding], dtype='float32')

        # Realiza la búsqueda en el índice
        distances, indices = self.index.search(query_embedding_np, top_k)

        # Extraer los documentos correspondientes, evitando índices fuera de rango
        results = [self.documents[idx][1] for idx in indices[0] if idx < len(self.documents)]
        return results

    def close(self):
        pass  # No se necesita cerrar nada para FAISS en local




