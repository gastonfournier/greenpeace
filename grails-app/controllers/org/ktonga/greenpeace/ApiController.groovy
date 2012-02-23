package org.ktonga.greenpeace

import grails.converters.JSON
import grails.converters.XML

import org.springframework.dao.DataIntegrityViolationException


class ApiController {

	static allowedMethods = [environments: "GET", projects: "GET"]

	def environments() {
		def environments = Environment.list()
		this.doFormat(environments)
	}

	def projects() {
		def projects = Project.list()
		this.doFormat(projects)
	}

	def effectiveProperties() {
		def project = Project.findByName(params.project)
		def props = project.getConfiguration(params.env).getOverrides(params.tag)
		def effective = props.effectiveProperties()
		def result = effective.collect {[key: it.key, value: it.value, usingDefault: it.usingDefault]}
		doFormat(result, [props: props])

	}

	def save() {
		def environmentInstance = new Environment(params)
		if (!environmentInstance.save(flush: true)) {
			render(view: "create", model: [environmentInstance: environmentInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'environment.label', default: 'Environment'),
			environmentInstance.id
		])
		redirect(action: "show", id: environmentInstance.id)
	}

	def show() {
		def environment = Environment.findByName(params.name)
		if (!environment) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'environment.label', default: 'Environment'),
				params.id
			])
			redirect(action: "list")
			return
		}
		this.doFormat(environment)
	}

	def update() {
		def environmentInstance = Environment.get(params.id)
		if (!environmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'environment.label', default: 'Environment'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (environmentInstance.version > version) {
				environmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'environment.label', default: 'Environment')]
						as Object[],
						"Another user has updated this Environment while you were editing")
				render(view: "edit", model: [environmentInstance: environmentInstance])
				return
			}
		}

		environmentInstance.properties = params

		if (!environmentInstance.save(flush: true)) {
			render(view: "edit", model: [environmentInstance: environmentInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'environment.label', default: 'Environment'),
			environmentInstance.id
		])
		redirect(action: "show", id: environmentInstance.id)
	}

	def delete() {
		def environmentInstance = Environment.get(params.id)
		if (!environmentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'environment.label', default: 'Environment'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			environmentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'environment.label', default: 'Environment'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'environment.label', default: 'Environment'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}

	private def doFormat(def result) {
		this.doFormat(result, null)
	}

	private def doFormat(def result, def model) {
		withFormat {
			html model
			properties model
			xml { render result as XML }
			json { render result as JSON }
		}
	}
}
