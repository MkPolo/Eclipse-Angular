package com.idat.semana13.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.idat.semana13.data.Mensaje;
import com.idat.semana13.data.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {

	private static final List<Usuario> USUARIOS = new ArrayList<Usuario>();
	static {
		USUARIOS.add(new Usuario(1L, "Paolo", "Guerrero Gonzales", 35));
		USUARIOS.add(new Usuario(2L, "Lionel", "Messi", 33));
		USUARIOS.add(new Usuario(3L, "Ivan", "Rackitic", 32));
		USUARIOS.add(new Usuario(4L, "Luis", "Suarez Días", 34));
	}

	private static final Response NO_ENCONTRADO = Response.status(Response.Status.NOT_FOUND)
			.entity(new Mensaje("El usuario no existe")).build();

	//private static final Response USUARIO_EXISTE = Response.status(Response.Status.BAD_REQUEST)
	//		.entity(new Mensaje("El usuario ya existe")).build();

	private static final Response OK = Response.status(Response.Status.OK)
			.entity(new Mensaje("Operacion realizada correctamente")).build();

	@GET
	@Path("/")
	@Produces( MediaType.APPLICATION_JSON )
	public Response listar() {
		return Response.status(Response.Status.OK).entity(USUARIOS).build();
	}

	@GET
	@Path("/{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public Response obtenerPorId( @PathParam("id") Long id) {
		
		Optional<Usuario> opt = USUARIOS.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
		if(opt.isPresent())
			return Response.status(Response.Status.OK).entity(opt.get()).build();
		return NO_ENCONTRADO;
	}

	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registrar(Usuario nuevousuario) {
		Long id = USUARIOS.get(USUARIOS.size() - 1).getId() + 1;
		nuevousuario.setId(id);
		USUARIOS.add(nuevousuario);
		return OK;
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response modificar( @PathParam("id") Long id, Usuario usuarioModificado) {
		Optional<Usuario> opt = USUARIOS.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
		if(!opt.isPresent())
			return NO_ENCONTRADO;		
		for (Usuario usuario : USUARIOS) {
			if(usuario.getId().equals(id)) {
				usuario.setNombres(usuarioModificado.getNombres());
				usuario.setApellidos(usuarioModificado.getApellidos());
				usuario.setEdad(usuarioModificado.getEdad());
				break;
			}
		}
		return Response.status(Response.Status.OK).entity(opt.get()).build();
		
	}

	@DELETE
	@Path("/{id}")
	public Response eliminar(@PathParam("id") Long id) {
		Optional<Usuario> opt = USUARIOS.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
		if(!opt.isPresent())
			return NO_ENCONTRADO;
		for (Usuario usuario : USUARIOS) {
			if(usuario.getId().equals(id)) {
				USUARIOS.remove(usuario);
				break;
			}
		}
		return OK;
	}

}
